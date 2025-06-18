package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/reviews")
@Transactional
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(
        value = "/reviewscreatereview",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Review createReview(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateReviewCommand createReviewCommand
    ) throws Exception {
        System.out.println("##### /review/createReview  called #####");
        Review review = new Review();
        review.createReview(createReviewCommand);
        reviewRepository.save(review);
        return review;
    }

    @RequestMapping(
        value = "/reviews/{id}/updatereview",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Review updateReview(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateReviewCommand updateReviewCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /review/updateReview  called #####");
        Optional<Review> optionalReview = reviewRepository.findById(id);

        optionalReview.orElseThrow(() -> new Exception("No Entity Found"));
        Review review = optionalReview.get();
        review.updateReview(updateReviewCommand);

        reviewRepository.save(review);
        return review;
    }

    @RequestMapping(
        value = "/reviews/{id}/deletereview",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Review deleteReview(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /review/deleteReview  called #####");
        Optional<Review> optionalReview = reviewRepository.findById(id);

        optionalReview.orElseThrow(() -> new Exception("No Entity Found"));
        Review review = optionalReview.get();
        review.deleteReview();

        reviewRepository.delete(review);
        return review;
    }
}
//>>> Clean Arch / Inbound Adaptor
