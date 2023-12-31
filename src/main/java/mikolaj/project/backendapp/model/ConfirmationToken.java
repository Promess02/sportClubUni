package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Integer id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        this.confirmationToken = TokenGenerator.generateToken();
        this.createdDate = Instant.now();
    }
    public ConfirmationToken() {

    }

    private static class TokenGenerator {
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        private static final int TOKEN_LENGTH = 12;

        private static Set<String> generatedTokens = new HashSet<>();

        public static String generateToken() {
            String token;
            do {
                token = generateRandomToken();
            } while (!generatedTokens.add(token)); // Ensure the token is unique

            return token;
        }

        private static String generateRandomToken() {
            SecureRandom random = new SecureRandom();
            StringBuilder token = new StringBuilder(TOKEN_LENGTH);

            for (int i = 0; i < TOKEN_LENGTH; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                token.append(CHARACTERS.charAt(randomIndex));
            }

            return token.toString();
        }
    }
}