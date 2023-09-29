import React, { useEffect, useState } from "react";
import api from "../../api/axiosConfig";

const ReviewList = ({ imdbId }) => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const response = await api.get(`/api/v1/reviews/${imdbId}`);
        setReviews(response.data);
      } catch (error) {
        console.error("Error fetching reviews:", error);
      }
    };

    fetchReviews();
  }, [imdbId]); // Fetch reviews when the IMDb ID changes

  return (
    <div>
      <h2>Reviews</h2>
      <ul>
        {reviews.map((review) => (
          <li key={review.id.timestamp}>{review.body}</li>
        ))}
      </ul>
    </div>
  );
};

export default ReviewList;
