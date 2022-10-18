package com.example.dynamicdatasource;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final static LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    private final UserService userService;

    private final DataSource dataSource;

    private final DefaultDataSourceCreator dataSourceCreator;

    private void printlog() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }

    @GetMapping("/log")
    public void log() {
        printlog();
        loggerContext.getLogger("com.example.dynamicdatasource").setLevel(Level.TRACE);
        printlog();
        loggerContext.getLogger("com.example").setLevel(Level.ERROR);
        printlog();
        ((ch.qos.logback.classic.Logger) log).setLevel(Level.ERROR);
        printlog();
    }

    @GetMapping("/create")
    public User create() {
        return userService.createUserByName("test");
    }

    @GetMapping("/get")
    public User get() {
        return userService.getUserById(1L).orElse(new User());
    }

    @GetMapping("/createAdmin")
    public AdminUser createAdmin() {
        return userService.createAdminUserByName("admin");
    }

    @GetMapping("/getAdmin")
    public Optional<AdminUser> getAdmin() {
        return userService.getAdminUserByIdForUpdate(2L);
    }

    @GetMapping("/datasource/add1")
    public Set<String> add1() {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl("jdbc:mysql://127.0.0.1:3306/test_1?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&useSSL=false");
        dataSourceProperty.setUsername("root");
        dataSourceProperty.setPassword("123456");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource("test_1", dataSource);
        return ds.getDataSources().keySet();
    }

    @GetMapping("/datasource/add2")
    public Set<String> add2() {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl("jdbc:mysql://127.0.0.1:3306/test_2?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&useSSL=false");
        dataSourceProperty.setUsername("root");
        dataSourceProperty.setPassword("123456");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource("test_2", dataSource);
        return ds.getDataSources().keySet();
    }

    @GetMapping("/create1")
    public User create1() {
        DynamicDataSourceContextHolder.push("test_1");//手动切换
        return userService.createUserByName("test1");
    }

    @GetMapping("/create2")
    public User create2() {
        DynamicDataSourceContextHolder.push("test_2");//手动切换
        return userService.createUserByName("test2");
    }
}
