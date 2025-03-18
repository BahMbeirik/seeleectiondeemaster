import { Component, OnInit } from '@angular/core';
import { CandidatService } from '../../services/candidat.service';
import { Candidat, Etablissement, Parcours } from 'src/app/models/candidat.model';
import { Router } from '@angular/router';
import { EtablissementService } from 'src/app/services/etablissement.service';

@Component({
  selector: 'app-candidats',
  templateUrl: './candidats.component.html',
  styleUrls: ['./candidats.component.css']
})
export class CandidatsComponent implements OnInit {
  candidats: Candidat[] = [];
  isModalOpen: boolean = false;
  selectedCandidat: any = {};
  currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;
  searchQuery: string = '';
  errorMessage: string | null = null;

  etablissements: Etablissement[] = [];
  filieres: string[] = ['IRM', 'MIAGE', 'DA2I', 'MI', 'MA', 'DI', 'IG', 'GLSI', 'RT', 'STATISTIQUE', 'FINANCE'];
  

  openModal(candidat: any) {
    this.selectedCandidat = { ...candidat };
    if (!this.selectedCandidat.etablissement) {
      this.selectedCandidat.etablissement = { id: null }; // تهيئة كائن `etablissement` إذا كان غير موجود
    }
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  constructor(private candidatService: CandidatService,private router: Router,private etablissementService: EtablissementService) { }

  ngOnInit(): void {
    this.obtenirTousLesCandidats();
    this.loadEtablissements();
  }

  loadEtablissements(): void {
    this.etablissementService.getAllEtablissements().subscribe(
      (data) => (this.etablissements = data),
      (error) => console.error('Erreur lors du chargement des établissements', error)
    );
  }

  // obtenirTousLesCandidats(): void {
  //   this.candidatService.obtenirTousLesCandidats().subscribe(
  //     data => this.candidats = data,
  //     error => console.error(error)
  //   );
  // }

  obtenirTousLesCandidats(): void {
    this.candidatService.getPaginatedCandidats(this.currentPage, this.pageSize).subscribe({
      next: (data: any) => {
        this.candidats = data.content; // Les candidats paginés
        this.totalPages = data.totalPages; // Nombre total de pages
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'Erreur lors du chargement des candidats';
      }
    });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.obtenirTousLesCandidats();
  }

  
  updateCandidat() {
    // تأكد من أن `etablissement` يحتوي فقط على `id`
    const payload = {
      ...this.selectedCandidat,
      etablissement: { id: this.selectedCandidat.etablissement.id } // إرسال `id` فقط
    };
  
    this.candidatService.modifierCandidat(this.selectedCandidat.id, payload).subscribe(
      () => {
        this.obtenirTousLesCandidats();
        this.closeModal();
      },
      error => {
        console.error(error);
        this.errorMessage = 'Failed to update candidat. Please check the data and try again.';
      }
    );
  }


  supprimerCandidat(id: number): void {
    this.candidatService.supprimerCandidat(id).subscribe(
      () => this.obtenirTousLesCandidats(),
      error => console.error(error)
    );
  }

  searchCandidats(): void {
    if (this.searchQuery.trim()) {
      this.candidatService.searchCandidats(this.searchQuery).subscribe({
        next: data => this.candidats = data,
        error: err => {
          console.error(err);
          this.errorMessage = 'Erreur lors de la recherche des candidats';
        }
      });
    } else {
      this.obtenirTousLesCandidats(); // Recharger tous les candidats si la recherche est vide
    }
  }
  
}
