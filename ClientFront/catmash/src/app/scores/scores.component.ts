import { Component, OnInit } from '@angular/core';
import { Cat } from '../model/cat';
import { CatService } from '../services/cat.service';

@Component({
  selector: 'app-scores',
  templateUrl: './scores.component.html',
  styleUrl: './scores.component.scss',
})
export class ScoresComponent implements OnInit {
  cats: Cat[] = [];

  constructor(private catService: CatService) {}

  ngOnInit(): void {
    this.loadCats();
  }

  loadCats(): void {
    this.catService.getAllCatsWithPodium().subscribe((data) => {
      this.cats = data;
    });
  }
}
