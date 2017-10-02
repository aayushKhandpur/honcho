package creativei.service.impl;

import creativei.dao.UserDao;
import creativei.entity.AuthUser;
import creativei.entity.FplusUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("UserDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FplusUser account = userDao.findByUserId(username);

        if(account != null) {
            AuthUser authUser = new AuthUser(account.getUserId(), account.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList(account.getUserRole().toString()), account.getRestaurant());
            return authUser;
        } else {
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }
    }
}
