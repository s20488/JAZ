import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../contracts/movie';
import { ActivatedRoute, Router } from '@angular/router';
import { Language } from 'src/app/contracts/language';

@Component({
  selector: 'app-update-movie',
  templateUrl: './update-movie.component.html',
  styleUrls: ['./update-movie.component.css']
})
export class UpdateMovieComponent implements OnInit {

  id: number;
  movie: Movie = new Movie();
  languages : Language[] = [];
  constructor(private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.movieService.getlanguages()
    .subscribe(data=>{
      this.languages=data;
      console.log("languages donwloaded.")
      this.movieService.getMovieById(this.id).subscribe(data => {
        this.movie = data;
      },
      error=>{
        alert(
          `
          Z adresu: 
              http://localhost:8080/api/v1/movies/${this.id}
          HttP Method GET
  
          chcę pobrać dane o filmie w postaci MovieDto:
          {
            id: number;
            title: string;
            homepage: string;
            language: string; 
            languageId: number;  
            adult:boolean;
            budget:number;
            overview: string;
            releaseDate: Date;
            runtime: number;
            languageId:number;
          }
          `
      );});

    },
      error=>{
        alert(`
        Z adresu:
        Request URL:http://localhost:8080/api/v1/movies/languages
        
        Request Method:GET
        Chcę pobrać listę języków, które są przedstawione w postaci LanguageDto:
          {
            id:number;
            name:string;
          }

        `)
      });

  }

  onSubmit(){
    this.movieService.updateMovie(this.id, this.movie).subscribe(data =>{
      this.goToMoviesList();
    }
    , 
    error=>{
      alert(
        `
        Z adresu: 
            http://localhost:8080/api/v1/movies/${this.id}
        
            HttP Method PUT

        chcę zaktualizować dane o filmie w postaci MovieDto:
        {
          id: number;
          title: string;
          homepage: string;
          language: string;  
          adult:boolean;
          budget:number;
          overview: string;
          releaseDate: Date;
          runtime: number;
          languageId:number;
        }
        `
      );});
  }

  goToMoviesList(){
    this.router.navigate(['/movies']);
  }
}
