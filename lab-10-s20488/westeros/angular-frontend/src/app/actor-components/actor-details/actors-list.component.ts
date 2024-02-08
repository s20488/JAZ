import { Component, OnInit } from '@angular/core';
import {MovieService} from "../../movie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ActorRole} from "../../contracts/actor-role";

@Component({
  selector: 'actors-list',
  templateUrl: './actors-list.component.html',
  styleUrls: ['./actors-list.component.css']
})
export class ActorsListComponent implements OnInit {

  id: number;
  actors: Array<ActorRole> = [];
  constructor(
    private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.movieService.getActorsList(this.id)
      .subscribe(
        data=>{
          this.actors=data;
        },
        error => console.log(error)
      )
  }


}
