import { Car } from '../model/Car';
import { Comment } from '../model/Comment';
import { PriceList } from './PriceList';

export class Ad {
    id : number;
    car: Car = new Car();
    city: String = "";
    collisionDamageWaiver : boolean;
    userID: number;
    startDate: string = "";
    endDate: string = "";
    comments: Comment[] = [];
    rating: number;
    priceList: PriceList;
    priceListID: number;
}