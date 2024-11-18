import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CatService } from './services/cat.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  buttonText: string = '';
  buttonLink: string = '';
  matchCount: number = 0;

  constructor(private router: Router, private catService: CatService) {}
  ngOnInit(): void {
    this.updateButtonState();
    this.getMatchCount();

    this.router.events.subscribe(() => {
      this.updateButtonState();
    });
  }

  updateButtonState(): void {
    if (this.router.url === '/vote') {
      this.buttonText = 'Voir le classement des chats';
      this.buttonLink = '/scores';
    } else if (this.router.url === '/scores') {
      this.buttonText = 'Revenir au vote';
      this.buttonLink = '/vote';
    }
  }

  getMatchCount(): void {
    this.catService.getTotalMatchesPlayed().subscribe((count) => {
      this.matchCount = count;
    });
  }

  updateMatchCount(): void {
    this.getMatchCount();
  }
}
