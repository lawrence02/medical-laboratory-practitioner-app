import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'medicalLaboratoryPractitionerApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'practitioner',
    data: { pageTitle: 'medicalLaboratoryPractitionerApp.practitioner.home.title' },
    loadChildren: () => import('./practitioner/practitioner.routes'),
  },
  {
    path: 'qualification',
    data: { pageTitle: 'medicalLaboratoryPractitionerApp.qualification.home.title' },
    loadChildren: () => import('./qualification/qualification.routes'),
  },
  {
    path: 'payment',
    data: { pageTitle: 'medicalLaboratoryPractitionerApp.payment.home.title' },
    loadChildren: () => import('./payment/payment.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
