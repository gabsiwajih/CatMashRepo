import { Component, OnInit } from '@angular/core';
import { CatService } from '../services/cat.service';
import { AppComponent } from '../app.component';
import { Cat } from '../model/cat';

@Component({
  selector: 'app-vote',
  templateUrl: './vote.component.html',
  styleUrl: './vote.component.scss',
})
export class VoteComponent implements OnInit {
  cats: Cat[] = [];

  constructor(private catService: CatService, private app: AppComponent) {}

  ngOnInit(): void {
    this.loadTwoCats();
  }

  loadTwoCats(): void {
    this.catService.getTwoRandomCats().subscribe((data) => {
      this.cats = data;
    });
  }

  vote(catId: string): void {
    this.catService.voteForCat(catId).subscribe(() => {
      this.loadTwoCats();
      this.app.getMatchCount();
    });
  }
}
