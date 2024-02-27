package Ptyxiaki.Repositories;

import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Entities.Post;
import Ptyxiaki.Enums.JobType;
import Ptyxiaki.Enums.WorkLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCreatedBy(AppUser user, Pageable pageable);
    Page<Post> findByJobType(JobType jobType, Pageable pageable);
    Page<Post> findByWorkLocation(WorkLocation workLocation, Pageable pageable);
    Page<Post> findByJobTypeAndWorkLocation(JobType jobType, WorkLocation workLocation, Pageable pageable);
}
