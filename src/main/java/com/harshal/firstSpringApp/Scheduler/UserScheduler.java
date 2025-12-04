package com.harshal.firstSpringApp.Scheduler;

import com.harshal.firstSpringApp.Repository.UserRespositoryImpl;
import com.harshal.firstSpringApp.Services.EmailService;
import com.harshal.firstSpringApp.entity.JournalEntry;
import com.harshal.firstSpringApp.entity.User;
import com.harshal.firstSpringApp.enums.Sentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRespositoryImpl userRespository;

//    @Scheduled(cron = "*/1 * * * * ?")
    public void fetchUserAndSendSM() {
        List<User> users = userRespository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().
                            isAfter(LocalDateTime.now().minusDays(7))).map(x -> x.getSentiment()).
                    toList();
            Map<Sentiment, Integer> sentimentsCount = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentsCount.put(sentiment, sentimentsCount.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentsCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Your Sentiment Report of 7 days"
                        , mostFrequentSentiment.toString());
            }
        }
    }
}
