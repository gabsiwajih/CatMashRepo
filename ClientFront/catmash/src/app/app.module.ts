import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VoteComponent } from './vote/vote.component';
import { ScoresComponent } from './scores/scores.component';
import { CatService } from './services/cat.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [AppComponent, VoteComponent, ScoresComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [CatService],
  bootstrap: [AppComponent],
})
export class AppModule {}
