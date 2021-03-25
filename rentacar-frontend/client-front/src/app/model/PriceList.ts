import { Ad } from './Ad';

export class PriceList {
    id: number;
    startDate: string;
    endDate: string;
    price: number;
    collisionDamageWaiver: number;
    pricePerKm: number;
    ads: Ad[] = [];
}