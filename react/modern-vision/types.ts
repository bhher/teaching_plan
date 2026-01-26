
export interface SlideItem {
  id: number;
  title: string;
  subtitle: string;
  imageUrl: string;
  accentColor: string;
}

export interface CardItem {
  id: number;
  title: string;
  description: string;
  category: string;
  imageUrl: string;
  price?: string;
}
