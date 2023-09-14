package wang.zihlu.security.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wang.zihlu.security.model.proto.User;
import wang.zihlu.security.repository.UserRepository;
import wang.zihlu.security.service.UserService;

import static wang.zihlu.security.model.proto.table.UserTableDef.USER;

/**
 * UserServiceImpl
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserDetailsService, UserService  {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getOne(QueryWrapper.create()
                .select()
                .where(USER.USERNAME.eq(username)));
    }

}
