import styles from './ModuleDemo.module.scss';

/** CSS Module: 클래스명이 빌드 시 고유해져 다른 컴포넌트와 충돌하지 않음 */
export default function ModuleDemo() {
  return (
    <section className={styles.wrapper}>
      <h2 className={styles.title}>2) CSS Module (ModuleDemo.module.scss)</h2>
      <p className={styles.note}>
        <code>styles.title</code> 등은 빌드 후 <code>ModuleDemo_title__xxx</code> 처럼 바뀝니다.
      </p>
    </section>
  );
}
