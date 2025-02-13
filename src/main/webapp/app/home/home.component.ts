import { Component, OnDestroy, OnInit, inject, signal } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { SortByDirective, SortDirective, SortService, SortState, sortStateSignal } from 'app/shared/sort';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import SharedModule from 'app/shared/shared.module';
import { AccountService } from 'app/core/auth/account.service';
import { PractitionerService } from 'app/entities/practitioner/service/practitioner.service';
import { Account } from 'app/core/auth/account.model';
import { IDashboardCount } from 'app/entities/practitioner/practitioner.model';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  imports: [SharedModule, RouterModule],
})
export default class HomeComponent implements OnInit, OnDestroy {
  counts = signal<IDashboardCount[] | null>(null);
  account = signal<Account | null>(null);
  isLoading = signal(false);
  totalItems = signal(0);
  itemsPerPage = 25;
  page!: number;
  sortState = sortStateSignal({});
  countsData: IDashboardCount[] = [];

  private readonly destroy$ = new Subject<void>();
  private readonly sortService = inject(SortService);
  private readonly accountService = inject(AccountService);
  private readonly practitionerService = inject(PractitionerService);
  private readonly router = inject(Router);

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => {
        this.account.set(account);
        if (account != null) {
          // eslint-disable-next-line no-console
          console.log('Counts:', account);
          this.loadAll();
        }
      });
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  loadAll(): void {
    this.practitionerService.queryDashboardCount().subscribe({
      next: (res: HttpResponse<IDashboardCount[]>) => {
        this.counts.set(res.body ?? null);
        const currentCounts = this.counts();
        if (currentCounts) {
          this.countsData.push(...currentCounts);
        }
      },
      error: () => {
        this.counts.set(null);
        this.countsData = [];
      },
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private onSuccess(counts: IDashboardCount[] | null, headers: HttpHeaders): void {
    this.totalItems.set(Number(headers.get('X-Total-Count')));
    this.counts.set(counts);
  }
}
