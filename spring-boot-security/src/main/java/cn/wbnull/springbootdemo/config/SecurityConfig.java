package cn.wbnull.springbootdemo.config;

import cn.wbnull.springbootdemo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityConfig
 *
 * @author dukunbiao(null)  2024-03-12
 * https://github.com/dkbnull/SpringBootDemo
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求授权的规则
        //开启认证
        http.authorizeRequests()
                //首页所有人可访问
                .antMatchers("/").permitAll()
                //功能页对应角色或权限才能访问
                //hasRole 为角色授权，表示用户拥有指定角色
                //hasAuthority 为权限授权，表示用户拥有指定权限
                .antMatchers("/level-1.html").hasRole("level1")
                .antMatchers("/level-2.html").hasRole("level2")
                .antMatchers("/level-3.html").hasAuthority("level3");

        //开启登录，无权限时进入登录页面
        //自定义登录页，默认用户参数是username，默认密码参数是password，无修改可不配置usernameParameter、passwordParameter
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password");
        //关闭csrf防护
        http.csrf().disable();

        //记住我
        //自定义登录页，默认记住我参数是remember-me，无修改可不配置rememberMeParameter
        http.rememberMe().rememberMeParameter("remember-me");

        //开启注销，注销成功后回首页
        http.logout().logoutSuccessUrl("/");

        //自定义403页面
        http.exceptionHandling().accessDeniedPage("/403.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //密码加密
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        //配置用户名、密码，该配置方式下，用户名和密码保存在内存中
//        auth.inMemoryAuthentication()
//                //密码加密方式
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(passwordEncoder.encode("123456")).roles("admin")
//                .and().withUser("admin1").password(passwordEncoder.encode("123456")).roles("level1")
//                .and().withUser("admin2").password(passwordEncoder.encode("123456")).roles("level2")
//                .and().withUser("admin3").password(passwordEncoder.encode("123456")).authorities("level3")
//                .and().withUser("admin0").password(passwordEncoder.encode("123456")).authorities("ROLE_level1", "ROLE_level2", "level3");

        //使用UserDetailsServiceImpl 查询用户名、密码
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
