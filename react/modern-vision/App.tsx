
import React from 'react';
import Header from './components/Header';
import HeroSlider from './components/HeroSlider';
import CardSection from './components/CardSection';
import Footer from './components/Footer';

const App: React.FC = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      <main className="flex-grow">
        <HeroSlider />
        
        {/* Trust Section */}
        <div className="bg-white py-12 border-b border-slate-100">
          <div className="container mx-auto px-6 flex flex-wrap justify-center md:justify-between items-center gap-8 grayscale opacity-50 hover:grayscale-0 hover:opacity-100 transition-all duration-500">
            {['Samsung', 'Google', 'Apple', 'Meta', 'Amazon', 'Tesla'].map(brand => (
              <span key={brand} className="text-2xl font-black text-slate-800">{brand}</span>
            ))}
          </div>
        </div>

        <CardSection />

        {/* CTA Section */}
        <section className="py-24 bg-indigo-600 relative overflow-hidden">
          <div className="absolute top-0 right-0 -mt-20 -mr-20 w-96 h-96 bg-white/10 rounded-full blur-3xl animate-pulse" />
          <div className="absolute bottom-0 left-0 -mb-20 -ml-20 w-96 h-96 bg-indigo-400/20 rounded-full blur-3xl" />
          
          <div className="container mx-auto px-6 relative z-10 text-center">
            <h2 className="text-4xl md:text-5xl font-bold text-white mb-8">
              지금 바로 비전과 함께<br />성장의 발걸음을 떼어보세요.
            </h2>
            <p className="text-indigo-100 text-xl mb-12 max-w-2xl mx-auto">
              수천 개의 성공적인 프로젝트 경험이 귀하의 비즈니스를 지원합니다.<br />
              무료 컨설팅을 통해 가능성을 확인하세요.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <button className="px-10 py-4 bg-white text-indigo-600 rounded-full font-bold text-lg hover:shadow-xl transition-all">
                프로젝트 시작하기
              </button>
              <button className="px-10 py-4 bg-transparent border-2 border-white/30 text-white rounded-full font-bold text-lg hover:bg-white/10 transition-all">
                포트폴리오 더보기
              </button>
            </div>
          </div>
        </section>
      </main>
      <Footer />
    </div>
  );
};

export default App;
