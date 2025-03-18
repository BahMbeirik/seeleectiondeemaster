export interface Candidat {
  numeroIouBac: string;
  moyenneBac: string;
  moyenneLicence: string;
  diplomeBacPath: string;
  diplomeLicencePath: string;
  releve1Path: string;
  releve2Path: string;
  releve3Path: string;
  releve4Path: string;
  releve5Path: string;
  releve6Path: string;
  etatValidation1: string;
  etatValidation2: string;
  etatValidation3: string;
  etatValidation4: string;
  etatValidation5: string;
  etatValidation6: string;
  id: number;
  nom: string;
  prenom: string;
  anneeBac: number;
  anneeLicence: number;
  moyenneReleve1: number;
  moyenneReleve2: number;
  moyenneReleve3: number;
  moyenneReleve4: number;
  moyenneReleve5: number;
  moyenneReleve6: number;
  moyenneClassement?: number;
  filier: string;
  parcours: string;
  statut?: string;
  etablissement: {
    id: number;
    nom: string;
  };
  parcoursChoisis: Parcours[];
  bonnus?: number;
  malus?: number;
  penalite?: number;
}

export interface Parcours {
  id?: number;
  nom: string;
  quota: number;
}
export interface Etablissement {
  id: number| null;
  nom: string;
}