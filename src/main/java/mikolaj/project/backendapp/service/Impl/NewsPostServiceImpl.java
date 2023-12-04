package mikolaj.project.backendapp.service.Impl;

import mikolaj.project.backendapp.DTO.NewsPostForm;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Location;
import mikolaj.project.backendapp.model.MembershipType;
import mikolaj.project.backendapp.model.NewsPost;
import mikolaj.project.backendapp.repo.ActivityRepo;
import mikolaj.project.backendapp.repo.LocationRepo;
import mikolaj.project.backendapp.repo.MembershipTypeRepo;
import mikolaj.project.backendapp.repo.NewsPostRepo;
import mikolaj.project.backendapp.service.NewsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsPostServiceImpl implements NewsPostService {
    private final NewsPostRepo newsPostRepo;
    private final ActivityRepo activityRepo;
    private final LocationRepo locationRepo;
    private final MembershipTypeRepo membershipTypeRepo;

    @Autowired
    public NewsPostServiceImpl(NewsPostRepo newsPostRepo, ActivityRepo activityRepo, LocationRepo locationRepo, MembershipTypeRepo membershipTypeRepo) {
        this.newsPostRepo = newsPostRepo;
        this.activityRepo = activityRepo;
        this.locationRepo = locationRepo;
        this.membershipTypeRepo = membershipTypeRepo;
    }

    @Override
    public ServiceResponse<?> addNewsPost(NewsPostForm newsPostForm) {
        Location location = null;
        Activity activity = null;
        MembershipType membershipType = null;
        if(newsPostForm.getLocationId()!=null) {
            Optional<Location> locationFound = locationRepo.findById(newsPostForm.getLocationId());
            if(locationFound.isEmpty()) return new ServiceResponse<>(Optional.empty(),"no location found with provided id");
            location = locationFound.get();
        }
        if(newsPostForm.getActivityId()!=null) {
            Optional<Activity> activityFound = activityRepo.findById(newsPostForm.getActivityId());
            if(activityFound.isEmpty()) return new ServiceResponse<>(Optional.empty(),"no activity found with provided id");
            activity = activityFound.get();
        }
        if(newsPostForm.getMembershipTypeId()!=null) {
            Optional<MembershipType> membershipTypeFound = membershipTypeRepo.findById(newsPostForm.getMembershipTypeId());
            if(membershipTypeFound.isEmpty()) return new ServiceResponse<>(Optional.empty(),"no membership type found with provided id");
            membershipType = membershipTypeFound.get();
        }

        NewsPost newsPost = new NewsPost(newsPostForm.getTitle(), newsPostForm.getContent(),
                newsPostForm.getImageUrl(), location,membershipType,activity);

        newsPostRepo.save(newsPost);

        return new ServiceResponse<>(Optional.of(newsPost), "news post successfully created");
    }

    @Override
    public ServiceResponse<List<NewsPost>> viewAllNewsPost() {
        List<NewsPost> newsPostList = newsPostRepo.findAll();
        if(newsPostList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No news post found");
        return new ServiceResponse<>(Optional.of(newsPostList),"news posts successfully retrieved");
    }

    @Override
    public ServiceResponse<?> viewRecentNewsPosts(int years, int months, int days) {
        List<NewsPost> newsPostList = newsPostRepo.findAll();
        if(newsPostList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No news post found");
        List<NewsPost> filteredList = newsPostList.stream().filter(newsPost -> newsPost.getDateOfPosting().isAfter(LocalDate.now()
                .minusYears(years).minusMonths(months).minusDays(days))).toList();

        if(filteredList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No news post found with given filter");
        return new ServiceResponse<Object>(Optional.of(filteredList), "successfully retrieved filtered list for date");
    }

    @Override
    public ServiceResponse<?> viewPostsForActivity(Integer activityId, int years, int months, int days) {
        if(activityId==null) return new ServiceResponse<>(Optional.empty(), "no activity id provided");
        Optional<Activity> activityOpt = activityRepo.findById(activityId);
        if(activityOpt.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no activity found with given id");
        Activity activity = activityOpt.get();
        List<NewsPost> newsPostList = newsPostRepo.findAll();
        List<NewsPost> filteredList = newsPostList.stream().filter(newsPost -> newsPost.getDateOfPosting().isAfter(LocalDate.now()
                .minusYears(years).minusMonths(months).minusDays(days)))
                .filter(newsPost -> newsPost.getActivity().equals(activity)).toList();
        if(filteredList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No news post found with given filter");
        return new ServiceResponse<Object>(Optional.of(filteredList), "successfully retrieved filtered list for date and activity");
    }

    @Override
    public ServiceResponse<?> viewPostsForMembershipType(Integer membershipTypeId,int years, int months, int days) {
        if(membershipTypeId==null) return new ServiceResponse<>(Optional.empty(), "no membership type id provided");
        Optional<MembershipType> membershipTypeOpt = membershipTypeRepo.findById(membershipTypeId);
        if(membershipTypeOpt.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no membership type found with given id");
        MembershipType membershipType = membershipTypeOpt.get();
        List<NewsPost> newsPostList = newsPostRepo.findAll();
        List<NewsPost> filteredList = newsPostList.stream().filter(newsPost -> newsPost.getDateOfPosting().isAfter(LocalDate.now()
                        .minusYears(years).minusMonths(months).minusDays(days)))
                .filter(newsPost -> newsPost.getMembershipType().equals(membershipType)).toList();
        if(filteredList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No news post found with given filter");
        return new ServiceResponse<Object>(Optional.of(filteredList), "successfully retrieved filtered list for date and membership type");
    }

    @Override
    public ServiceResponse<?> viewPostsForLocation(Integer locationId, int years, int months, int days) {
        if(locationId==null) return new ServiceResponse<>(Optional.empty(), "no location id provided");
        Optional<Location> locationOpt = locationRepo.findById(locationId);
        if(locationOpt.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no location found with given id");
        Location location = locationOpt.get();
        List<NewsPost> newsPostList = newsPostRepo.findAll();
        List<NewsPost> filteredList = newsPostList.stream().filter(newsPost -> newsPost.getDateOfPosting().isAfter(LocalDate.now()
                        .minusYears(years).minusMonths(months).minusDays(days)))
                .filter(newsPost -> newsPost.getLocation().equals(location)).toList();
        if(filteredList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No news post found with given filter");
        return new ServiceResponse<Object>(Optional.of(filteredList), "successfully retrieved filtered list for date and location");
    }
}
