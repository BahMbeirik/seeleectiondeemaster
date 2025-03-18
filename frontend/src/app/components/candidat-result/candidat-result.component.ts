import { Component, OnInit } from '@angular/core';
import { Candidat } from 'src/app/models/candidat.model';
import { CandidatService } from 'src/app/services/candidat.service';

@Component({
  selector: 'app-candidat-result',
  templateUrl: './candidat-result.component.html',
  styleUrls: ['./candidat-result.component.css']
})
export class CandidatResultComponent implements OnInit {
  candidatsOrganises: { [key: string]: { [key: string]: { [key: string]: Candidat[] } } } = {};

  constructor(private candidatService: CandidatService) { }

  ngOnInit(): void {
    this.candidatService.getCandidatsOrganises().subscribe(data => {
      this.candidatsOrganises = data;
    });
  }
}
