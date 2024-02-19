package Ptyxiaki.Services.Implementation;

import Ptyxiaki.Repositories.IPostRepository;
import Ptyxiaki.Repositories.IUserRepository;
import Ptyxiaki.Services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserRepository userRepository;
}
