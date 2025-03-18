import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Candidat } from '../models/candidat.model';

@Injectable({
  providedIn: 'root'
})
export class CandidatService {

  private apiUrl = 'http://localhost:8080/api/candidats';

  constructor(private http: HttpClient) { }

  obtenirTousLesCandidats(): Observable<Candidat[]> {
    return this.http.get<Candidat[]>(this.apiUrl);
  }

  obtenirCandidatParId(id: number): Observable<Candidat> {
    return this.http.get<Candidat>(`${this.apiUrl}/${id}`);
  }

  ajouterCandidat(formData: FormData): Observable<Candidat> {
    return this.http.post<Candidat>(this.apiUrl, formData);
  }


  modifierCandidat(id: number, candidat: Candidat): Observable<Candidat> {
    return this.http.put<Candidat>(`${this.apiUrl}/${id}`, candidat);
  }

  supprimerCandidat(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }


  getPaginatedCandidats(page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/paginated?page=${page}&size=${size}`);
  }

  searchCandidats(query: string): Observable<Candidat[]> {
    return this.http.get<Candidat[]>(`${this.apiUrl}/search?query=${query}`);
  }

  getCandidatsOrganises(): Observable<any> {
    return this.http.get(`${this.apiUrl}/organises`);
  }
}