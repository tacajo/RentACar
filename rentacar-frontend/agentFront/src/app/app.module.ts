import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AdvancedSearchComponent } from './advanced-search/advanced-search.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { LoginService } from './service/login.service';
import { CreateReportComponent } from './create-report/create-report.component';
import { AdListComponent } from './ad-list/ad-list.component';
import { ModalDetailsComponent } from './modal-details/modal-details.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MessagesComponent } from './messages/messages.component';
import { ModalMessagesComponent } from './modal-messages/modal-messages.component';
import { NewAdComponent } from './new-ad/new-ad.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { ChartsModule } from 'ng2-charts';
import { CreatePaidRequestComponent } from './create-paid-request/create-paid-request.component';
import { ConfirmAccountComponent } from './confirm-account/confirm-account.component';
import { RequestListComponent } from './request-list/request-list.component';
import { PriceListComponent } from './price-list/price-list.component';
import { PriceListViewComponent } from './price-list-view/price-list-view.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CartListComponent } from './cart-list/cart-list.component';
import { CodebookComponent } from './codebook/codebook.component';



@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    AdvancedSearchComponent,
    LoginComponent,
    RegistrationComponent,
    CreateReportComponent,
    AdListComponent,
    ModalDetailsComponent,
    MessagesComponent,
    ModalMessagesComponent,
    NewAdComponent,
    StatisticsComponent,
    CreatePaidRequestComponent,
    ConfirmAccountComponent,
    RequestListComponent,
    PriceListComponent,
    PriceListViewComponent,
    CartListComponent,
    CodebookComponent
  ],
  entryComponents: [ModalDetailsComponent, ModalMessagesComponent,PriceListComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    ChartsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
    ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
