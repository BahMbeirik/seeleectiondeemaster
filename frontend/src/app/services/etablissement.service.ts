import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Etablissement } from '../models/etablisement.module';

@Injectable({
  providedIn: 'root'
})
export class EtablissementService {

  private apiUrl = 'http://localhost:8080/api/etablissements';

  constructor(private http: HttpClient) { }

  // Récupérer tous les établissements
  getAllEtablissements(): Observable<Etablissement[]> {
    return this.http.get<Etablissement[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching établissements:', error);
        if (error.error instanceof ErrorEvent) {
          // Client-side error
          console.error('Client-side error:', error.error.message);
        } else {
          // Server-side error
          console.error(`Server-side error: ${error.status} - ${error.message}`);
          console.error('Response body:', error.error);
        }
        return throwError(() => new Error('Failed to fetch établissements.'));
      })
    );
  }

  // Récupérer un établissement par son ID
  getEtablissementById(id: number): Observable<Etablissement> {
    return this.http.get<Etablissement>(`${this.apiUrl}/${id}`);
  }

  // Ajouter ou mettre à jour un établissement
  saveEtablissement(etablissement: Etablissement): Observable<Etablissement> {
    if (etablissement.id) {
      // Mise à jour
      return this.http.put<Etablissement>(`${this.apiUrl}/${etablissement.id}`, etablissement);
    } else {
      // Création
      return this.http.post<Etablissement>(this.apiUrl, etablissement);
    }
  }

  createEtablissement(etablissement: Etablissement): Observable<Etablissement> {
    return this.http.post<Etablissement>(`${this.apiUrl}`, etablissement);
  }
  
  updateEtablissement(etablissement: Etablissement): Observable<Etablissement> {
    return this.http.put<Etablissement>(`${this.apiUrl}/${etablissement.id}`, etablissement);
  }

  // Supprimer un établissement
  deleteEtablissement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}