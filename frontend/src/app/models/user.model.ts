export interface User {
    id: number;
    email: string;
    password?: string;
    role: 'admin' | 'candidat' | 'jury' | 'agent';
  }
  