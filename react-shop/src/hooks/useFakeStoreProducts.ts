import { useEffect, useState } from 'react';
import axios from 'axios';

export type FakeStoreProduct = {
  id: number;
  title: string;
  price: number;
  description: string;
  category: string;
  image: string;
  rating?: {
    rate: number;
    count: number;
  };
};

const FALLBACK_PRODUCTS: FakeStoreProduct[] = [
  {
    id: 1,
    title: 'K-컬처 한복 스타일 원피스',
    price: 89.0,
    description: '일상에서도 입기 좋은 모던 한복 스타일 원피스',
    category: "women's clothing",
    image:
      'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=800&q=80',
  },
  {
    id: 2,
    title: '전통 노리개 액세서리 세트',
    price: 29.0,
    description: '한복뿐 아니라 가방, 키링으로도 활용 가능한 노리개 세트',
    category: 'jewelery',
    image:
      'https://images.unsplash.com/photo-1515560570411-00a0026e6088?auto=format&fit=crop&w=800&q=80',
  },
  {
    id: 3,
    title: '감성 도자기 머그컵',
    price: 19.0,
    description: '따뜻한 티타임을 위한 수공예 도자기 머그컵',
    category: 'electronics',
    image:
      'https://images.unsplash.com/photo-1517705008128-361805f42e86?auto=format&fit=crop&w=800&q=80',
  },
  {
    id: 4,
    title: '한국 전통 패턴 티셔츠',
    price: 35.0,
    description: '전통 문양을 현대적으로 재해석한 캐주얼 티셔츠',
    category: "men's clothing",
    image:
      'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=800&q=80',
  },
];

type FetchState = {
  data: FakeStoreProduct[];
  loading: boolean;
  error: string | null;
};

export const useFakeStoreProducts = () => {
  const [state, setState] = useState<FetchState>({
    data: [],
    loading: true,
    error: null,
  });

  useEffect(() => {
    let cancelled = false;

    const fetchProducts = async () => {
      setState((prev) => ({ ...prev, loading: true, error: null }));

      try {
        const res = await axios.get<FakeStoreProduct[]>('https://fakestoreapi.com/products', {
          timeout: 8000,
        });
        if (cancelled) return;
        setState({ data: res.data, loading: false, error: null });
      } catch (error) {
        if (cancelled) return;
        // 네트워크나 CORS 문제로 실시간 API를 못 불러올 때는
        // 예시용 더미 데이터를 대신 보여주도록 처리
        setState({
          data: FALLBACK_PRODUCTS,
          loading: false,
          error: '실시간 API 호출에 실패해서 예시 상품을 대신 보여주고 있습니다.',
        });
      }
    };

    fetchProducts();

    return () => {
      cancelled = true;
    };
  }, []);

  return state;
};

