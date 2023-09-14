package wang.zihlu.security.repository;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wang.zihlu.security.model.proto.User;

/**
 * UserRepository
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
public interface UserRepository extends BaseMapper<User> {
}
