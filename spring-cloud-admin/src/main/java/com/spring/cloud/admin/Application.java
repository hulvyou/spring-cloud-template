package com.spring.cloud.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import de.codecentric.boot.admin.notify.LoggingNotifier;
import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import de.codecentric.boot.admin.notify.filter.FilteringNotifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * 启动类
 *
 * @author Travel Hu
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
            http.logout().logoutUrl("/logout");
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
                    .permitAll();
            http.authorizeRequests().antMatchers("/**").authenticated();
            http.httpBasic();
        }
    }

    @Configuration
    public static class NotifierConfig {
        @Bean
        @Primary
        public RemindingNotifier remindingNotifier() {
            RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(loggerNotifier()));
            notifier.setReminderPeriod(TimeUnit.SECONDS.toMillis(10));
            return notifier;
        }

        @Scheduled(fixedRate = 1_000L)
        public void remind() {
            remindingNotifier().sendReminders();
        }

        @Bean
        public FilteringNotifier filteringNotifier(Notifier delegate) {
            return new FilteringNotifier(delegate);
        }

        @Bean
        public LoggingNotifier loggerNotifier() {
            return new LoggingNotifier();
        }
    }
}
