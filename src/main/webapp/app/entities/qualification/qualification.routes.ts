import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import QualificationResolve from './route/qualification-routing-resolve.service';

const qualificationRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/qualification.component').then(m => m.QualificationComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/qualification-detail.component').then(m => m.QualificationDetailComponent),
    resolve: {
      qualification: QualificationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/qualification-update.component').then(m => m.QualificationUpdateComponent),
    resolve: {
      qualification: QualificationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/qualification-update.component').then(m => m.QualificationUpdateComponent),
    resolve: {
      qualification: QualificationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default qualificationRoute;
