import './MovieForm.css';
import { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import api from "../../api/axiosConfig";

const MovieForm = ({ onClose }) => {

    const [formData, setFormData] = useState({
        title : "",
        releaseDate: "",
        trailerLink : "",
        imdbId : "",
        genres : [],
        poster : "",
        backdrops : [],
        reviewsIds : [] 
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        if(name === 'backdrops'){
            const updatedValue = name === 'backdrops' ? value.split(', ') : value;
            setFormData({ ...formData, [name]: updatedValue });
        }else{
            setFormData({ ...formData, [name] : value});
        }
        
    };

    const handleGenreChange = (e) => {
        const { name, options } = e.target;
        const selectedGenres = Array.from(options)
            .filter((option) => option.selected)
            .map((option) => option.value);

        setFormData({ ...formData, [name]: selectedGenres });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            
            const response = await api.post("api/v1/movies/addmovie", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            });

            if(response.ok){
                onClose();
            }else{
                console.error(`Failed to add Movie. Status code: ${response.status}`);
                console.error("Response:", response);
            }

        } catch (error) {
            console.log("An error occured", error);
        }
    };

  return (
    <Modal show={true} onHide={onClose}>
        <Modal.Header closeButton>
            <Modal.Title>Add Movie</Modal.Title>
        </Modal.Header>
        <Modal.Body className="movie-form">
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="title">
                    <Form.Label>Title</Form.Label>
                    <Form.Control
                        type="text"
                        name="title"
                        value={formData.title}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>
                <Form.Group controlId="imdbId">
                    <Form.Label>ImdbId</Form.Label>
                    <Form.Control
                        type="text"
                        name="imdbId"
                        value={formData.imdbId}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>
                <Form.Group controlId="releaseData">
                    <Form.Label>Release Date</Form.Label>
                    <Form.Control
                        type="date"
                        name="releaseDate"
                        value={formData.releaseDate}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>
                <Form.Group controlId="trailerLink">
                    <Form.Label>Trailer Link</Form.Label>
                    <Form.Control
                    type="text"
                    name="trailerLink"
                    value={formData.trailerLink}
                    onChange={handleChange}
                    required
                    />
                </Form.Group>
                <Form.Group controlId="poster">
                    <Form.Label>Poster</Form.Label>
                    <Form.Control
                    type="text"
                    name="poster"
                    value={formData.poster}
                    onChange={handleChange}
                    required
                    />
                </Form.Group>
                <Form.Group controlId="genres">
                    <Form.Label>Genres</Form.Label>
                        <Form.Control
                            as="select"
                            name="genres"
                            value={formData.genres}
                            onChange={handleGenreChange}
                            multiple
                            required
                            >
                            <option value="Action">Action</option>
                            <option value="Adventure">Adventure</option>
                            <option value="Comedy">Comedy</option>
                            <option value="Fantasy">Fantasy</option>
                            <option value="Horror">Horror</option>
                            <option value="Triller">Triller</option>
                        </Form.Control>
                </Form.Group>
                <Form.Group controlId="backdrops">
                    <Form.Label>Backdrops (comma-separated)</Form.Label>
                    <Form.Control
                        type="text"
                        name="backdrops"
                        value={formData.backdrops.join(', ')}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>
                <Form.Group controlId="submit">
                    <Button className="form-submit-button" type="submit" variant="primary">Submit</Button>
                </Form.Group>
            </Form>
        </Modal.Body>
    </Modal>
  )
}

export default MovieForm