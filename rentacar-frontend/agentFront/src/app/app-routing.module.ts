import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { AdvancedSearchComponent } from './advanced-search/advanced-search.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { CreateReportComponent } from './create-report/create-report.component';
import { NewAdComponent } from './new-ad/new-ad.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { CreatePaidRequestComponent } from './create-paid-request/create-paid-request.component';
import { ConfirmAccountComponent } from './confirm-account/confirm-account.component';
import { PriceListComponent } from './price-list/price-list.component';



const routes: Routes = [ 
      { path: 'token/:id', component: HomePageComponent },
      { path: '', component: HomePageComponent },
      { path: 'advancedSearch', component: AdvancedSearchComponent },
      { path: 'login', component:  LoginComponent},
      { path: 'register', component:  RegistrationComponent},
      { path: 'report', component: CreateReportComponent },
      { path: 'newAd', component: NewAdComponent },
      { path: 'statistics', component: StatisticsComponent },
      { path : 'paidRequest' , component: CreatePaidRequestComponent},
      { path: 'confirm-account', component: ConfirmAccountComponent},
      {path: 'newPriceList' , component: PriceListComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
