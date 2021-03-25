import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { AdvancedSearchComponent } from './advanced-search/advanced-search.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { NewAdComponent } from './new-ad/new-ad.component';
import { CreateReportComponent } from './create-report/create-report.component';
import { SingleSignOnComponent } from './single-sign-on/single-sign-on.component';
import { ConfirmAccountComponent } from './confirm-account/confirm-account.component';
import { PriceListComponent } from './price-list/price-list.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'advancedSearch', component: AdvancedSearchComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'newAd', component: NewAdComponent },
  { path: 'report', component: CreateReportComponent },
  { path: 'single-sign-on', component: SingleSignOnComponent },
  { path: 'confirm-account', component: ConfirmAccountComponent},
  {path: 'newPriceList' , component: PriceListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
