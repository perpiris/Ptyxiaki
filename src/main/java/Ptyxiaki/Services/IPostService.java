package Ptyxiaki.Services;

import Ptyxiaki.Dtos.PostDto;
import Ptyxiaki.Enums.JobType;
import Ptyxiaki.Enums.WorkLocation;
import org.springframework.data.domain.Page;

public interface IPostService {

    Page<PostDto> findAll(int pageNumber, int pageSize, String sortBy);
    Page<PostDto> findByJobType(JobType jobType, int pageNumber, int pageSize, String sortBy);
    Page<PostDto> findByWorkLocation(WorkLocation workLocation, int pageNumber, int pageSize, String sortBy);
    Page<PostDto> findByJobTypeAndWorkLocation(JobType jobType, WorkLocation workLocation, int pageNumber, int pageSize, String sortBy);
    Page<PostDto> findAllForManager(int pageNumber, int pageSize, String sortBy);
    PostDto get(final Long id);
    void create(PostDto postDto);
    void update(final Long id, final PostDto postDTO);
    void delete(final Long id);
    void applyToPost(Long postId);
}
