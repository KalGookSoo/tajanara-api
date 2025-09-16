package app.vercel.tajanara.config;

import app.vercel.tajanara.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"test"})
@RequiredArgsConstructor
@Configuration
public class AppInitializer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @Override
    public void run(String... args) {
        logger.info("애플리케이션을 시작합니다.");
        logger.info("시스템 관리자 계정을 생성합니다.");
        userService.initialSystemUsers();
    }

}
