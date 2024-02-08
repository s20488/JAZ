import { Component, OnInit } from '@angular/core';
import { Movie } from '../../contracts/movie';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  id: number
  movie: Movie
  constructor(private route: ActivatedRoute, private movieService: MovieService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.movie = new Movie();
    this.movieService.getMovieById(this.id).subscribe(data => {
      this.movie = data;
    });
  }

}
