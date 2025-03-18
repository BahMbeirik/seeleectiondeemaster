import { Component, OnInit } from '@angular/core';
import { ParcoursService } from '../../services/parcours.service';
import { Parcours } from 'src/app/models/parcours.module';

@Component({
  selector: 'app-parcours',
  templateUrl: './parcours.component.html',
  styleUrls: ['./parcours.component.css']
})
export class ParcoursComponent implements OnInit {

  parcoursList: Parcours[] = [];
  selectedParcours: Parcours = { nom: '', quota: 0 };
  isEditMode = false;

  constructor(private parcoursService: ParcoursService) { }

  ngOnInit(): void {
    this.loadParcours();
  }

  // Charger la liste des parcours
  loadParcours(): void {
    this.parcoursService.getAllParcours().subscribe(data => {
      this.parcoursList = data;
    });
  }

  // Ajouter ou mettre à jour un parcours
  saveParcours(): void {
    this.parcoursService.saveParcours(this.selectedParcours).subscribe(() => {
      this.loadParcours();
      this.resetForm();
    });
  }

  // Modifier un parcours
  editParcours(parcours: Parcours): void {
    this.selectedParcours = { ...parcours };
    this.isEditMode = true;
  }

  // Supprimer un parcours
  deleteParcours(id: number): void {
    this.parcoursService.deleteParcours(id).subscribe(() => {
      this.loadParcours();
    });
  }

  // Réinitialiser le formulaire
  resetForm(): void {
    this.selectedParcours = { nom: '', quota: 0 };
    this.isEditMode = false;
  }
}