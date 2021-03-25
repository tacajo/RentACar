import { RentACar } from './RentACar';
import { User } from './User';

export class OwnerRequest {
    id;
    startDate;
    rentACars : RentACar[] = [];
    user: User = new User;
    status;
    paidDate;
}