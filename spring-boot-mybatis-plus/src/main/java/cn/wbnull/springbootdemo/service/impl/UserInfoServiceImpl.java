package cn.wbnull.springbootdemo.service.impl;

import cn.wbnull.springbootdemo.entity.UserInfo;
import cn.wbnull.springbootdemo.mapper.UserInfoMapper;
import cn.wbnull.springbootdemo.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author null
 * @since 2024-02-19
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
