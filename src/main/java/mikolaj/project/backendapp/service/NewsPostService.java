package mikolaj.project.backendapp.service;

import mikolaj.project.backendapp.DTO.NewsPostForm;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.NewsPost;

import java.util.List;

public interface NewsPostService {

    ServiceResponse<?> addNewsPost(NewsPostForm newsPostForm);

    ServiceResponse<List<NewsPost>> viewAllNewsPost();

    ServiceResponse<?> viewRecentNewsPosts(int years, int months, int days);

    ServiceResponse<?> viewPostsForActivity(Integer activityId, int years, int months, int days);
    ServiceResponse<?> viewPostsForMembershipType(Integer membershipTypeId, int years, int months, int days);
    ServiceResponse<?> viewPostsForLocation(Integer locationId, int years, int months, int days);
}
