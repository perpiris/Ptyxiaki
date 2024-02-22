package Ptyxiaki.Services.Implementation;

import Ptyxiaki.Dtos.PostDto;
import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Entities.Post;
import Ptyxiaki.Repositories.IPostRepository;
import Ptyxiaki.Repositories.IUserRepository;
import Ptyxiaki.Security.SecurityUtility;
import Ptyxiaki.Services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Ptyxiaki.Exceptions.NotFoundException;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserRepository userRepository;

    public Page<PostDto> findAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.map(post -> mapToDto(post, new PostDto()));
    }

    public Page<PostDto> findAllForManager(int pageNumber, int pageSize, String sortBy) {
        String username = SecurityUtility.getSessionUser();
        AppUser user = userRepository.findByUsername(username);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> postPage = postRepository.findAllByCreatedBy(user, pageable);
        return postPage.map(post -> mapToDto(post, new PostDto()));
    }

    public PostDto get(final Long id) {
        return postRepository.findById(id)
                .map(post -> mapToDto(post, new PostDto()))
                .orElseThrow(NotFoundException::new);
    }

    public void create(final PostDto postDTO) {
        final Post post = new Post();
        mapToEntity(postDTO, post);

        String username = SecurityUtility.getSessionUser();
        AppUser user = userRepository.findByUsername(username);
        post.setCreatedBy(user);

        postRepository.save(post);
    }

    public void update(final Long id, final PostDto postDTO) {
        final Post post = postRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(postDTO, post);
        postRepository.save(post);
    }

    public void delete(final Long id) {
        postRepository.deleteById(id);
    }

    private PostDto mapToDto(final Post post, final PostDto postDto) {
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setJobType(post.getJobType());
        postDto.setWorkLocation(post.getWorkLocation());
        return postDto;
    }

    private void mapToEntity(final PostDto postDTO, final Post post) {
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setJobType(postDTO.getJobType());
        post.setWorkLocation(postDTO.getWorkLocation());
    }
}
