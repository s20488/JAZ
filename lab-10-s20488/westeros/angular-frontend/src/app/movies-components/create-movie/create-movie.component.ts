import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Router } from '@angular/router';
import {Movie} from "../../contracts/movie";

@Component({
  selector: 'app-create-movie',
  templateUrl: './create-movie.component.html',
  styleUrls: ['./create-movie.component.css']
})
export class CreateMovieComponent implements OnInit {

  movie: Movie = new Movie();
  constructor(private movieService: MovieService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveMovie(){
    this.movieService.createMovie(this.movie).subscribe(data =>{
      console.log(data);
      this.goToMoviesList();
    },
    error => console.log(error));
  }

  goToMoviesList(){
    this.router.navigate(['/movies']);
  }

  onSubmit(){
    console.log(this.movie);
    this.saveMovie();
  }
}
