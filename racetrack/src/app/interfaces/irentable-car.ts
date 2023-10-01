export interface IRentableCar {
  id: string;
  brand: string;
  model: string;
  price: number;
  startingProductionYear: number;
  carBody: string;
  doors: number;
  seats: number;
  trunkCapacity: number;
  weight: number;
  length: number;
  width: number;
  height: number;
  wheelDistance: number;
  engine: string;
  displacement: number;
  fuel: string;
  maxPowerEngineSpeed: string;
  maxTorque: number;
  driveType: string;
  gearbox: string;
  gears: number;
  topSpeed: number;
  northToSixty: number;
  carImgPath: string;
  drivable: boolean;
}
