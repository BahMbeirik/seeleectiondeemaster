import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parcours } from '../models/parcours.module';

@Injectable({
  providedIn: 'root'
})
export class ParcoursService {

  private apiUrl = 'http://localhost:8080/api/parcours';

  constructor(private http: HttpClient) { }

  // Récupérer tous les parcours
  getAllParcours(): Observable<Parcours[]> {
    return this.http.get<Parcours[]>(this.apiUrl);
  }

  // Récupérer un parcours par son ID
  getParcoursById(id: number): Observable<Parcours> {
    return this.http.get<Parcours>(`${this.apiUrl}/${id}`);
  }

  // Ajouter ou mettre à jour un parcours
  saveParcours(parcours: Parcours): Observable<Parcours> {
    return parcours.id
      ? this.http.put<Parcours>(`${this.apiUrl}/${parcours.id}`, parcours) // Mettre à jour
      : this.http.post<Parcours>(this.apiUrl, parcours); // Ajouter
  }

  // Supprimer un parcours
  deleteParcours(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}