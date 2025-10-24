package com.store.managementapplication;

import com.store.managementapplication.entities.Role;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class StoreManagementApplication implements ApplicationRunner {

    private final UserRepository userRepository;

    public StoreManagementApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        String adminEmail = "admin@example.com";

        // ✅ Only create admin if it doesn't already exist
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User superUser = new User(
                    "Molly",
                    "Brown",
                    adminEmail,
                    "adminPassword",
                    Role.RoleType.ADMIN
            );
            userRepository.save(superUser);
            System.out.println("✅ Default admin created: " + adminEmail);
        } else {
            System.out.println("ℹ️ Admin already exists, skipping creation.");
        }
    }
}
