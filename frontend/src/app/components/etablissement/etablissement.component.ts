import { Component, OnInit } from '@angular/core';
import { EtablissementService } from '../../services/etablissement.service';
import { Etablissement } from 'src/app/models/etablisement.module';

@Component({
  selector: 'app-etablissement',
  templateUrl: './etablissement.component.html',
  styleUrls: ['./etablissement.component.css']
})
export class EtablissementComponent implements OnInit {

  
  etablissements: Etablissement[] = [];
  selectedEtablissement: Etablissement = {id:null, nom: '' };
  isEditMode = false;

  constructor(private etablissementService: EtablissementService) { }

  ngOnInit(): void {
    this.loadEtablissements();
  }

  // Charger la liste des établissements
  loadEtablissements(): void {
    this.etablissementService.getAllEtablissements().subscribe({
      next: (data) => {
        this.etablissements = data;
      },
      error: (error) => {
        console.error('Error loading établissements:', error);
      }
    });
  }

  // Ajouter ou mettre à jour un établissement
  // saveEtablissement(): void {
  //   this.etablissementService.saveEtablissement(this.selectedEtablissement).subscribe({
  //     next: () => {
  //       this.loadEtablissements();
  //       this.resetForm();
  //     },
  //     error: (error) => {
  //       console.error('Error saving établissement:', error);
  //     }
  //   });
  // }
  saveEtablissement(): void {
    if (this.selectedEtablissement.id) {
      // Mise à jour
      this.etablissementService.updateEtablissement(this.selectedEtablissement).subscribe({
        next: () => {
          this.loadEtablissements();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error updating établissement:', error);
        }
      });
    } else {
      // Création
      this.etablissementService.createEtablissement(this.selectedEtablissement).subscribe({
        next: () => {
          this.loadEtablissements();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error creating établissement:', error);
        }
      });
    }
  }

  // Modifier un établissement
  editEtablissement(etablissement: Etablissement): void {
    this.selectedEtablissement = { ...etablissement };
    this.isEditMode = true;
  }

  // Supprimer un établissement
  deleteEtablissement(id: number): void {
    this.etablissementService.deleteEtablissement(id).subscribe({
      next: () => {
        this.loadEtablissements();
      },
      error: (error) => {
        console.error('Error deleting établissement:', error);
      }
    });
  }

  // Réinitialiser le formulaire
  resetForm(): void {
    this.selectedEtablissement = {id:null, nom: '' };
    this.isEditMode = false;
  }
}