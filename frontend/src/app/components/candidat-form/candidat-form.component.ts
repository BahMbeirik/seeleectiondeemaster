import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CandidatService } from '../../services/candidat.service';
import { EtablissementService } from '../../services/etablissement.service';
import { ParcoursService } from '../../services/parcours.service';
import { Candidat, Etablissement, Parcours } from 'src/app/models/candidat.model';

@Component({
  selector: 'app-candidat-form',
  templateUrl: './candidat-form.component.html',
  styleUrls: ['./candidat-form.component.css']
})
export class CandidatFormComponent implements OnInit {
  candidat: Candidat = {
    id: 0,
    nom: '',
    prenom: '',
    filier: '',
    numeroIouBac: '',
    anneeBac: 0,
    anneeLicence: 0,
    moyenneBac: '',
    moyenneLicence: '',
    diplomeBacPath: '',
    diplomeLicencePath: '',
    releve1Path: '',
    moyenneReleve1: 0,
    releve2Path: '',
    moyenneReleve2: 0,
    releve3Path: '',
    moyenneReleve3: 0,
    releve4Path: '',
    moyenneReleve4: 0,
    releve5Path: '',
    moyenneReleve5: 0,
    releve6Path: '',
    moyenneReleve6: 0,
    etatValidation1: '',
    etatValidation2: '',
    etatValidation3: '',
    etatValidation4: '',
    etatValidation5: '',
    etatValidation6: '',
    parcoursChoisis: [],
    etablissement: { id: 0, nom: '' },
    parcours: '',
    penalite: 0.0,
  };

  files: { [key: string]: File | null } = {
    diplomeBacPath: null,
    diplomeLicencePath: null,
    releve1Path: null,
    releve2Path: null,
    releve3Path: null,
    releve4Path: null,
    releve5Path: null,
    releve6Path: null
  };

  etablissements: Etablissement[] = [];
  parcoursList: Parcours[] = [];
  filieres: string[] = ['IRM', 'MIAGE', 'DA2I', 'MI', 'MA', 'DI', 'IG', 'GLSI', 'RT', 'STATISTIQUE', 'FINANCE'];

  constructor(
    private candidatService: CandidatService,
    private etablissementService: EtablissementService,
    private parcoursService: ParcoursService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadEtablissements();
    this.loadParcours();
  }

  loadEtablissements(): void {
    this.etablissementService.getAllEtablissements().subscribe(
      (data) => (this.etablissements = data),
      (error) => console.error('Erreur chargement établissements', error)
    );
  }

  loadParcours(): void {
    this.parcoursService.getAllParcours().subscribe(
      (data) => (this.parcoursList = data),
      (error) => console.error('Erreur chargement parcours', error)
    );
  }

  onFileChange(event: any, fileKey: string): void {
    this.files[fileKey] = event.target.files[0];
  }

  onSubmit(): void {
    const formData = new FormData();
  
    // Ajouter les champs texte
    Object.keys(this.candidat).forEach((key) => {
      if (typeof (this.candidat as any)[key] !== 'object') {
        formData.append(key, String((this.candidat as any)[key]));
      }
    });
  
    // Ajouter les fichiers
    Object.keys(this.files).forEach((key) => {
      if (this.files[key]) {
        formData.append(key, this.files[key] as File);
      }
    });
  
    // Ajouter l'ID de l'établissement
    formData.append('etablissementId', this.candidat.etablissement.id.toString());
  
    
  
    // Ajouter les IDs des parcours choisis
    const parcoursIds = this.candidat.parcoursChoisis.map(p => p.id).join(',');
    formData.append('parcoursIds', parcoursIds);
  
    this.candidatService.ajouterCandidat(formData).subscribe(
      (response) => {
        this.router.navigate(['/success']);
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
