package Ptyxiaki.Services.Implementation;

import Ptyxiaki.Dtos.PostDto;
import Ptyxiaki.Dtos.RequirementDto;
import Ptyxiaki.Entities.*;
import Ptyxiaki.Enums.ApplicationStatus;
import Ptyxiaki.Enums.JobType;
import Ptyxiaki.Enums.WorkLocation;
import Ptyxiaki.Repositories.IPostRepository;
import Ptyxiaki.Repositories.IApplicationRepository;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IApplicationRepository userPostApplicationRepository;

    public Page<PostDto> findAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.map(post -> mapToDto(post, new PostDto()));
    }

    public Page<PostDto> findByJobType(JobType jobType, int pageNumber, int pageSize, String sortBy) {
        if (jobType == null) {
            return findAll(pageNumber, pageSize, sortBy);
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> postPage = postRepository.findByJobType(jobType, pageable);
        return postPage.map(post -> mapToDto(post, new PostDto()));
    }

    public Page<PostDto> findByWorkLocation(WorkLocation workLocation, int pageNumber, int pageSize, String sortBy) {
        if (workLocation == null) {
            return findAll(pageNumber, pageSize, sortBy);
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> postPage = postRepository.findByWorkLocation(workLocation, pageable);
        return postPage.map(post -> mapToDto(post, new PostDto()));
    }

    public Page<PostDto> findByJobTypeAndWorkLocation(JobType jobType, WorkLocation workLocation, int pageNumber, int pageSize, String sortBy) {
        if (jobType == null && workLocation == null) {
            return findAll(pageNumber, pageSize, sortBy);
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> postPage = postRepository.findByJobTypeAndWorkLocation(jobType, workLocation, pageable);
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

    public void applyToPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(NotFoundException::new);
        String username = SecurityUtility.getSessionUser();
        AppUser user = userRepository.findByUsername(username);

        if (post.getCreatedBy().getId().equals(user.getId())) {
            throw new IllegalStateException("The user who created the post cannot apply to it.");
        }

        for (Application application : post.getApplications()) {
            if (application.getUser().equals(user)) {
                throw new IllegalArgumentException("You have already applied to this post.");
            }
        }

        Application application = Application.builder()
                .user(user)
                .post(post)
                .build();

        application.setStatus(ApplicationStatus.PENDING);

        userPostApplicationRepository.save(application);
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
        postDto.setCreatedOn(calculateRelativeTime(post.getCreatedOn()));
        postDto.setRequirements(post.getRequirements().stream()
                .map(this::mapRequirementToDto)
                .collect(Collectors.toList()));
        return postDto;
    }

    private void mapToEntity(final PostDto postDTO, final Post post) {
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setJobType(postDTO.getJobType());
        post.setWorkLocation(postDTO.getWorkLocation());
        post.setRequirements(postDTO.getRequirements().stream()
                .map(this::mapDtoToRequirement)
                .collect(Collectors.toList()));
    }

    private String calculateRelativeTime(LocalDateTime createdDate) {
        LocalDateTime now = LocalDateTime.now();
        long seconds = now.atZone(ZoneId.systemDefault()).toEpochSecond() - createdDate.atZone(ZoneId.systemDefault()).toEpochSecond();
        long days = seconds / (60 * 60 * 24);
        long hours = seconds / (60 * 60);
        long minutes = seconds / 60;
        if (days > 0) {
            return days + (days == 1 ? " day ago" : " days ago");
        } else if (hours > 0) {
            return hours + (hours == 1 ? " hour ago" : " hours ago");
        } else if (minutes > 0) {
            return minutes + (minutes == 1 ? " minute ago" : " minutes ago");
        } else {
            return seconds + (seconds == 1 ? " second ago" : " seconds ago");
        }
    }

    private RequirementDto mapRequirementToDto(Requirement requirement) {
        RequirementDto requirementDto = new RequirementDto();
        requirementDto.setLabel(requirement.getLabel());
        requirementDto.setMinimumYears(requirement.getMinimumYears());
        return requirementDto;
    }

    private Requirement mapDtoToRequirement(RequirementDto requirementDto) {
        Requirement requirement = new Requirement();
        requirement.setLabel(requirementDto.getLabel());
        requirement.setMinimumYears(requirementDto.getMinimumYears());
        return requirement;
    }
}
