import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cat } from '../model/cat';

@Injectable({
  providedIn: 'root',
})
export class CatService {
  private url = 'http://localhost:8080/api/cats';

  constructor(private http: HttpClient) {}

  getAllCatsWithPodium(): Observable<Cat[]> {
    return this.http.get<Cat[]>(`${this.url}/all`);
  }

  getTwoRandomCats(): Observable<Cat[]> {
    return this.http.get<Cat[]>(`${this.url}/vote`);
  }

  voteForCat(id: string): Observable<void> {
    return this.http.post<void>(`${this.url}/vote/${id}`, {});
  }

  getTotalMatchesPlayed(): Observable<number> {
    return this.http.get<number>(`${this.url}/votes`);
  }
}
